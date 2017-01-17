package beydogan.notiboard.controller;

import beydogan.notiboard.model.Board;
import beydogan.notiboard.repository.IBoardRepository;
import com.sun.media.jfxmedia.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/boards")
public class BoardsController {

    @Autowired
    private IBoardRepository repo;

    @GetMapping
    public String index(Model model){
        model.addAttribute("boards", repo.findAll());
        return "boards/index";
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable String id, Model model) {
        try {
            Board b = repo.findOne(Integer.valueOf(id));
            model.addAttribute("board", b);
        }catch(Exception e){
            model.addAttribute("board", null);
        }
        return "boards/show";

    }

    @RequestMapping("/new")
    public String _new(Model model) {
        model.addAttribute("board", new Board());
        return "boards/new";
    }

    @PostMapping
    public String create(@Valid Board board, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return "boards/new";

        try {
            repo.save(board);
            return "redirect:/boards";
        }catch(Exception e){
            return "boards/new";
        }
    }


}