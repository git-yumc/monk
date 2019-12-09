import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public class DataListener extends AnalysisEventListener<Student> {
    private List<Student> list = new ArrayList<>();

    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        list.add(student);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
