
package readingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Controller
@RequestMapping("/")


public class ReadingListController {
    
    private ReadingListRepository readingListRepository;
    
    @Autowired
    
    public ReadingListController(ReadingListRepository readingListRepository){
        this.readingListRepository = readingListRepository;
    }
    
    @RequestMapping(value = "/{reader}", method=RequestMethod.GET)
    
    public String readersBooks (@PathVariable String reader,Model model){
        
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null){
            model.addAttribute("books",readingList);
        }
        return "readingList";
    }
    
    @RequestMapping(value = "/{reader}",method=RequestMethod.POST)
    
    public String addReadingList(
            @PathVariable String reader,
            @RequestBody String body,String title,String author,String isbn,String description,
            Book book){
//        System.out.println(title);
//        System.out.println(author);
//        System.out.println(isbn);
//        System.out.println(description);
        book.setAuthor(author);
        book.setDescription(description);
        book.setTitle(title);
        book.setReader(reader);
        readingListRepository.save(book);

        return "redirect:/{reader}";
    }
    
  
}




