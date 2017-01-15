package beydogan.notiboard.controller;

import beydogan.notiboard.model.Board;
import beydogan.notiboard.repository.IBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private IBoardRepository repo;

    @RequestMapping("/")
    public String hello(@RequestParam(value="id", required=false) String id) {

        try {
            Board b = repo.findOne(Integer.valueOf(id));
            return b.getName();
        }catch(Exception e){
            return "Not Found";
        }
    }

}