package www.qingxiangyx.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import www.qingxiangyx.litemall.db.domain.Seckill;
import www.qingxiangyx.litemall.db.domain.SeckillExample;

public interface SeckillMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill
     *
     * @mbg.generated
     */
    long countByExample(SeckillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill
     *
     * @mbg.generated
     */
    int deleteByExample(SeckillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill
     *
     * @mbg.generated
     */
    int insert(Seckill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill
     *
     * @mbg.generated
     */
    int insertSelective(Seckill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill
     *
     * @mbg.generated
     */
    Seckill selectOneByExample(SeckillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill
     *
     * @mbg.generated
     */
    Seckill selectOneByExampleSelective(@Param("example") SeckillExample example, @Param("selective") Seckill.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill
     *
     * @mbg.generated
     */
    List<Seckill> selectByExampleSelective(@Param("example") SeckillExample example, @Param("selective") Seckill.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill
     *
     * @mbg.generated
     */
    List<Seckill> selectByExample(SeckillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill
     *
     * @mbg.generated
     */
    Seckill selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Seckill.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill
     *
     * @mbg.generated
     */
    Seckill selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Seckill record, @Param("example") SeckillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Seckill record, @Param("example") SeckillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Seckill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seckill
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Seckill record);

    int reduceInventory(@Param("seckillId") long seckillId, @Param("oldVersion") long oldVersion,
                        @Param("newVersion") long newVersion);
}