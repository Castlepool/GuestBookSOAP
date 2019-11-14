package GuestBook;

import java.util.HashMap;
import java.util.Map;

import hft.spring.guestbook.Entry;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class GuestBookRepository {
    private static Map<Integer, Entry> entries = new HashMap<>();

    private static GuestBookRepository instance = null;

    @PostConstruct
    public void initData(){
        Entry e1 = new Entry();
        e1.setId(1);
        e1.setName("Tjark");
        e1.setEmail("hallo@hallo.de");
        e1.setComment("Heyho");
        Entry e2 = new Entry();
        e2.setId(2);
        e2.setName("Samson");
        e2.setEmail("sam@s.on");
        e2.setComment("Isch mei name.");

        entries.put(e1.getId(), e1);
        entries.put(e2.getId(), e2);
    }

    static GuestBookRepository getInstance() {
        if (instance == null) {
            instance = new GuestBookRepository();
        }
        return instance;
    }

    private GuestBookRepository() {
        entries = new HashMap<>();
    }

    Map<Integer, Entry> fetchEntries() {
        return entries;
    }

    Entry getEntryById(int id) {
        return entries.get(id);
    }
}
