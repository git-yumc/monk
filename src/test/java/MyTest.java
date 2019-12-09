import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baizhi.monk.MonkApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = MonkApplication.class)
@RunWith(SpringRunner.class)
public class MyTest {
    private File file;
    private List<Student> list;

    @Before
    public void before() throws Exception {
        file = new File("C:\\Users\\yumcw\\Desktop\\test.xlsx");
        list=new ArrayList<>();
        list.add(new Student("王小师", 21, 2000d, new Date(), 1));
        list.add(new Student("师小王", 22, 1000d, new Date(), 0));
    }

    @Test
    public void test01(){
        /*
         * 第一种写入方法
         */
        //EasyExcel.write(file, Student.class).sheet("学生信息").doWrite(list);

        /*
         * 第二种写入方法
         */
        ExcelWriter excelWriter = EasyExcel.write(file, Student.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("学生信息").build();
        excelWriter.write(list, writeSheet);
        //一定要finish
        excelWriter.finish();
    }

    @Test
    public void test02(){
        ExcelReader excelReader = EasyExcel.read(file, Student.class, new DataListener()).build();

    }

    @Test
    public void myTest0(){
        System.out.println(System.currentTimeMillis());
    }


    @After
    public void after() throws Exception {
    }
}
