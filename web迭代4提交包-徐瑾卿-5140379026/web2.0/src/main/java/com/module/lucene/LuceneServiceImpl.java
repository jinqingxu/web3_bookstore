package com.module.lucene;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import java.util.ArrayList;
import java.util.List;

import com.module.bean.Comment;
import com.module.service.CommentService;
import javafx.util.Pair;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.jws.WebService;

/**
 *中文分词，IKAnalayzer,对索引结果实现高亮显示
 * @author YipFun
 */

public class LuceneServiceImpl implements LuceneService {
    private static final Version version = Version.LUCENE_4_9;
    private Directory directory = null;
    private DirectoryReader ireader = null;
    private IndexWriter iwriter = null;
    private IKAnalyzer analyzer;
    private CommentService commentService;

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    public CommentService getCommentService() {
        return commentService;
    }
    //测试数据
    /*private String[] content = {
            "你好，我是中共人！",
            "中华人民共和国",
            "中国人民从此站起来了",
            "Lucene是一个不错的全文检索的工具",
            "全文检索中文分词"
    };*/

    /**
     * 构造方法
     */
    public LuceneServiceImpl() {
        try {
            directory = new SimpleFSDirectory(new File("/Users/jinqingxu/Desktop/web2.0/lucene"));
            // 根据路径获取存储索引的目录
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private IKAnalyzer getAnalyzer(){
        if(analyzer == null){
            return new IKAnalyzer();
        }else{
            return analyzer;
        }
    }
   public void debug(){

       Document doc = null;
       try {
           IndexWriterConfig iwConfig =  new IndexWriterConfig(version, getAnalyzer());
           iwConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
           iwriter = new IndexWriter(directory,iwConfig);
            /*for(String text : content){
                doc = new Document();
                doc.add(new TextField("content", text,Field.Store.YES));
                iwriter.addDocument(doc);
            }*/

               doc = new Document();
               doc.add(new TextField("content", "好看", Field.Store.YES));
               doc.add(new TextField("bookname", "english", Field.Store.YES));
               iwriter.addDocument(doc);


       } catch (IOException e) {
           e.printStackTrace();
       }finally{
           try {
               if(iwriter != null)
                   iwriter.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }

    /**
     * 创建索引
     */
    public  void createIndex(){
        List<Comment> comments=this.commentService.getAllComment();
        Document doc = null;
        try {
            IndexWriterConfig iwConfig =  new IndexWriterConfig(version, getAnalyzer());
            iwConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
            iwriter = new IndexWriter(directory,iwConfig);
            /*for(String text : content){
                doc = new Document();
                doc.add(new TextField("content", text,Field.Store.YES));
                iwriter.addDocument(doc);
            }*/
            for(int i=0;i<comments.size();++i) {
                doc = new Document();
                doc.add(new TextField("content", comments.get(i).getContent(), Field.Store.YES));
                doc.add(new TextField("bookname", comments.get(i).getBookname(), Field.Store.YES));
                iwriter.addDocument(doc);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(iwriter != null)
                    iwriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public IndexSearcher getSearcher(){
        try {
            if(ireader==null) {
                ireader = DirectoryReader.open(directory);
            } else {
                DirectoryReader tr = DirectoryReader.openIfChanged(ireader) ;
                if(tr!=null) {
                    ireader.close();
                    ireader = tr;
                }
            }
            return new IndexSearcher(ireader);
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Comment> searchByTerm(String field, String keyword, int num){
        List<Comment>result=new ArrayList<Comment>();
        IndexSearcher isearcher = getSearcher();
        Analyzer analyzer =  getAnalyzer();
        //使用QueryParser查询分析器构造Query对象
        QueryParser qp = new QueryParser(version,
                field,analyzer);
        //这句所起效果？
        qp.setDefaultOperator(QueryParser.OR_OPERATOR);
        try {
            Query query = qp.parse(keyword);
            ScoreDoc[] hits;

            //注意searcher的几个方法
            hits = isearcher.search(query, null, num).scoreDocs;
            // 关键字高亮显示的html标签，需要导入lucene-highlighter-xxx.jar
            SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<span style='color:red'>", "</span>");
            Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));
            //length
            Fragmenter fragmenter = new SimpleFragmenter(1000);
            highlighter.setTextFragmenter(fragmenter);
            //
            for (int i = 0; i < hits.length; i++) {
                Document doc = isearcher.doc(hits[i].doc);
                // 内容增加高亮显示
                TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(doc.get("content")));
                String content = highlighter.getBestFragment(tokenStream, doc.get("content"));
                String bookname=doc.get("bookname");
                Comment comment=new Comment();
                comment.setBookname(bookname);
                comment.setContent(content);
                result.add(comment);
                System.out.println(content);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 使用过滤器查询
     * @param field
     * @param keyword
     * @param num
     * @throws InvalidTokenOffsetsException
     */
    public void searchByTermFilter(String field,String keyword,int num) {

            IndexSearcher isearcher = getSearcher();
            Analyzer analyzer = getAnalyzer();
            //使用QueryParser查询分析器构造Query对象
            QueryParser qp = new QueryParser(version,
                    field, analyzer);
            //这句所起效果？
            qp.setDefaultOperator(QueryParser.OR_OPERATOR);
            try {
                Query query = qp.parse(keyword);
                Query q2 = qp.parse("全文检索");
                ScoreDoc[] hits;

                QueryWrapperFilter filter = new QueryWrapperFilter(q2);
                //注意searcher的几个方法
                hits = isearcher.search(query, filter, num).scoreDocs;

                // 关键字高亮显示的html标签，需要导入lucene-highlighter-xxx.jar
                SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<span style='color:red'>", "</span>");
                Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));

                for (int i = 0; i < hits.length; i++) {
                    Document doc = isearcher.doc(hits[i].doc);
                    // 内容增加高亮显示
                    TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(doc.get("content")));
                    String content = highlighter.getBestFragment(tokenStream, doc.get("content"));
                    System.out.println(content);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    public static void main(String[] args) throws InvalidTokenOffsetsException {
        System.out.println("start");
        LuceneService ld = new LuceneServiceImpl();
        String bookname="chinese";
        String content="语文";
        String username="fname1";
        //ld.debug();
        //ld.createIndex(content,bookname,username);
        long start = System.currentTimeMillis();
        List<Comment> comments=ld.searchByTerm("content","好看",500);
        System.out.println("end search use "+(System.currentTimeMillis()-start)+"ms");
    }


}
