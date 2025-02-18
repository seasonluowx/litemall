package www.qingxiangyx.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import www.qingxiangyx.litemall.db.domain.LitemallCoupon;
import www.qingxiangyx.litemall.db.domain.LitemallCouponExample;

public interface LitemallCouponMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_coupon
     *
     * @mbg.generated
     */
    long countByExample(LitemallCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_coupon
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_coupon
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_coupon
     *
     * @mbg.generated
     */
    int insert(LitemallCoupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_coupon
     *
     * @mbg.generated
     */
    int insertSelective(LitemallCoupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_coupon
     *
     * @mbg.generated
     */
    LitemallCoupon selectOneByExample(LitemallCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_coupon
     *
     * @mbg.generated
     */
    LitemallCoupon selectOneByExampleSelective(@Param("example") LitemallCouponExample example, @Param("selective") LitemallCoupon.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_coupon
     *
     * @mbg.generated
     */
    List<LitemallCoupon> selectByExampleSelective(@Param("example") LitemallCouponExample example, @Param("selective") LitemallCoupon.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_coupon
     *
     * @mbg.generated
     */
    List<LitemallCoupon> selectByExample(LitemallCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_coupon
     *
     * @mbg.generated
     */
    LitemallCoupon selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallCoupon.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_coupon
     *
     * @mbg.generated
     */
    LitemallCoupon selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_coupon
     *
     * @mbg.generated
     */
    LitemallCoupon selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_coupon
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallCoupon record, @Param("example") LitemallCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_coupon
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallCoupon record, @Param("example") LitemallCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_coupon
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallCoupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_coupon
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallCoupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_coupon
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") LitemallCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_coupon
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}