import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class DemoDataListener extends AnalysisEventListener<DemoData> {
//    private static final Logger LOGGER = LoggerFactory.getLogger(DemoDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<DemoData> list = new ArrayList<DemoData>();

    public void invoke(DemoData data, AnalysisContext context) {
//        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        System.out.println("解析到一条数据:" + Thread.currentThread().getName());
        System.out.println(JSON.toJSONString(data));
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }


    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
//        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
//        LOGGER.info("{}条数据，开始存储数据库！", list.size());
//        LOGGER.info("存储数据库成功！");
    }
}
