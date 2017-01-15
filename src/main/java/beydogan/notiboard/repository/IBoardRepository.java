package beydogan.notiboard.repository;

import beydogan.notiboard.model.Board;
import org.springframework.data.repository.CrudRepository;

public interface IBoardRepository extends CrudRepository<Board, Integer> {

}
