package lt.vu.mybatis.dao;

import java.util.List;
import lt.vu.mybatis.model.Team;

public interface TeamMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TEAM
     *
     * @mbg.generated Mon Apr 20 22:50:03 EEST 2020
     */
    int deleteByPrimaryKey(byte[] id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TEAM
     *
     * @mbg.generated Mon Apr 20 22:50:03 EEST 2020
     */
    int insert(Team record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TEAM
     *
     * @mbg.generated Mon Apr 20 22:50:03 EEST 2020
     */
    Team selectByPrimaryKey(byte[] id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TEAM
     *
     * @mbg.generated Mon Apr 20 22:50:03 EEST 2020
     */
    List<Team> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TEAM
     *
     * @mbg.generated Mon Apr 20 22:50:03 EEST 2020
     */
    int updateByPrimaryKey(Team record);
}