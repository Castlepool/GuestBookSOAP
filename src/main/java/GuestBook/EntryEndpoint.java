package GuestBook;

import hft.spring.guestbook.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EntryEndpoint {
    private static final String NAMESPACE_URI = "http://spring.hft/guestbook";

    private  GuestBookRepository guestBookRepository;

    @Autowired
    public EntryEndpoint(GuestBookRepository guestBookRepository) {
        this.guestBookRepository = guestBookRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEntryRequest")
    @ResponsePayload
    public GetEntryResponse getEntry(@RequestPayload GetEntryRequest request) {
        GetEntryResponse response = new GetEntryResponse();
        response.setEntry(guestBookRepository.getEntryById(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createEntryRequest")
    @ResponsePayload
    public CreateEntryResponse createEntry(@RequestPayload CreateEntryRequest request) {
        int id = guestBookRepository.fetchEntries().size()+1;
        Entry newEntry = new Entry();
        newEntry.setId(id);
        newEntry.setName(request.getName());
        newEntry.setEmail(request.getEmail());
        newEntry.setComment(request.getComment());

        guestBookRepository.fetchEntries().put(id, newEntry);

        CreateEntryResponse response = new CreateEntryResponse();
        response.setId(id);
        return response;
    }
}
