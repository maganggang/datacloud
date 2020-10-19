package com.fei2e.filemanage.repository;

import com.fei2e.filemanage.entity.FileBase;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @ClassName FileBase
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/15 9:56
 * @Version 1.0
 **/
public interface FileBaseRepository extends JpaRepository<FileBase, Integer>, JpaSpecificationExecutor<FileBase>{
    /**
     * 根据code查询id
     * @param list
     * @return userList
     */
    @Modifying
    @Query(value = "select id from base where code in (:list)", nativeQuery = true)
    List<Integer> findByCode(@Param("list")List<String> list);
    /**
     * 演示@Query注解JPQL查询方式
     * @param id
     * @return
     */
    @Query(value = "select u from FileBase u where u.id = ?1")
    FileBase getOneByJpql(Integer id);

    /**
     * 演示@Query注解原生Sql查询方式
     * IDEA中使用原生Sql可能报错：
     * SQL dialect is not configured
     * 或：This inspection performs unresolved SQL references check
     * 注意配置好Database的数据源，指定数据库方言，指定Schema
     * @param id
     * @return
     */
    @Query(value = "select * from FileBase where id =:id", nativeQuery = true)
    FileBase getOneBySql(@Param("id") Integer id);

    /**
     * 条件查询 + 利用Pageable做分页查询
     * @param id
     * @param pageable
     * @return
     */
    @Query(value ="select u from FileBase u where u.creatorId > :id")
    Page<FileBase> queryUsers(@Param("id") Integer id, Pageable pageable);

    /**
     * 注解@Query用于更新、删除语句时必须搭配@Modifying注解，且返回值只能是void或者int/Integer
     * @param ids
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update Base u set u.use_Count=use_Count+1  where u.id  in (:ids)", nativeQuery = true)
    int updateCount(@Param("ids")List<Integer> ids);
    @Modifying
    @Query(value = "select * from Base u where id in (:list)", nativeQuery = true)
    List<FileBase> findByIds(@Param("list")List<Integer> list);
}
