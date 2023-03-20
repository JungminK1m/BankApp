package shop.mtcoding.bankapp.model.history;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.bankapp.dto.history.HistoryRespDto;

@Mapper
public interface HistoryRepository {
    public int insert(History user);

    public int updateById(History user);

    public int deleteById(int id);

    public List<History> findAll();

    public List<HistoryRespDto> findByGubun(@Param("gubun") String gubun, @Param("accountId") int accountId);

    public History findById(int id);
}