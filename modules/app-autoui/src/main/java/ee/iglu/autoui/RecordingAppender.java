package ee.iglu.autoui;

import ch.qos.logback.core.AppenderBase;

import java.util.ArrayList;
import java.util.List;

public class RecordingAppender<E> extends AppenderBase<E> {
    private static final ThreadLocal<List<Object>> record = ThreadLocal.withInitial(ArrayList::new);

    @Override
    protected void append(E eventObject) {
        record.get().add(eventObject);
    }

    public static void reset(){
        record.remove();
    }

    public static List<Object> getRecord(){
        return record.get();
    }
}
