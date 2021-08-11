package k_proxy.f;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-08-10 17:35
 **/
//解析一本书
class BookParser {

    public BookParser(String content) {
        this.content = content;
    }

    //接收一整本是的内容
    private String content;


    //计算动词的个数
    public int numberOfVerb() throws Exception {
        //模拟处理过程
        Thread.sleep(1000L);

        return 1340;
    }

    //计算名词的个数
    public int numberOfNoun() throws Exception {
        //模拟处理过程
        Thread.sleep(1000L);

        return 2510;
    }

}

class BookParserProxy extends BookParser {

    private Integer numberOfVerb;
    private Integer numberOfNoun;

    private BookParser bp;

    public BookParserProxy(String content, BookParser bp) {
        super(content);
        this.bp = bp;
    }



    @Override
    public int numberOfVerb() throws Exception {
        if (numberOfVerb == null) {
            numberOfVerb = bp.numberOfVerb();
        }
        return numberOfVerb;
    }

    @Override
    public int numberOfNoun() throws Exception {

        if (numberOfNoun== null) {
            numberOfNoun = bp.numberOfNoun();
        }
        return numberOfNoun;
    }
}

public class AppTest {
    public static void main(String[] args) throws Exception {
        String content = "天下大势，合久必分，分久必合。。。。。三国归晋";
        BookParserProxy proxy = new BookParserProxy(content,new BookParser(content));
        System.out.println(proxy.numberOfNoun());

        System.out.println(proxy.numberOfNoun());

    }
}
