package com.fei2e.getlost.base;

import com.fei2e.getlost.entity.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @ClassName BaseServiceImpl
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/27 10:43
 * @Version 1.0
 **/
@Service
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    protected abstract BaseMapper<T> getMapper();
    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     * @param record
     * @return
     */
    @Override
    public int insert(T record) {
        return getMapper().insert(record);
    }

    /**
     * 批量新增方法
     * @param list
     * @return
     */
    @Override
    public int insertList(List<T> list) {
       return getMapper().insertList(list);
    }
    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     * @param t
     * @return
     */
    @Override
    public int insertSelective(T t) {
        return getMapper().insertSelective(t);
    }
    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     * @param t
     * @return
     */
    @Override
    public T selectOne(T t) {
        return getMapper().selectOne(t);
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     * @param o
     * @return
     */
    @Override
    public T selectByPrimaryKey(Object o) {
        return getMapper().selectByPrimaryKey(o);
    }
    /**
     * 查询全部结果，select(null)方法能达到同样的效果
     * @return
     */
    @Override
    public List<T> selectAll() {
        return getMapper().selectAll();
    }
    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     * @param t
     * @return
     */
    @Override
    public List<T> select(T t) {
        return getMapper().select(t);
    }
    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     * @param t
     * @return
     */
    @Override
    public int selectCount(T t) {
        return getMapper().selectCount(t);
    }

    /**
     * 据Example条件进行查询总数
     * @param o
     * @return
     */
    @Override
    public int selectCountByExample(Object o) {
        return getMapper().selectCountByExample(o);
    }

    /**
     * 根据Condition条件进行查询
     * @param o
     * @return
     */
    @Override
    public int selectCountByCondition(Object o) {
        return getMapper().selectCountByCondition(o);
    }

    /**
     *  根据Example条件进行查询
     * @param o
     * @return
     */
    @Override
    public List<T> selectByExample(Object o) {
        return getMapper().selectByExample(o);
    }

    /**
     * 根据主键@Id进行查询，多个Id以逗号,分割
     * @param s
     * @return
     */
    @Override
    public List<T> selectByIds(String s) {
        return getMapper().selectByIds(s);
    }

    /**
     * 根据Condition条件进行查询
     * @param o
     * @return
     */
    @Override
    public List<T> selectByCondition(Object o) {
        return getMapper().selectByCondition(o);
    }

    /**
     * 根据Example条件进行查询，若有多条数据则抛出异常
     * @param o
     * @return
     */
    @Override
    public T selectOneByExample(Object o) {
        return getMapper().selectOneByExample(o);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     * @param o
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Object o) {
        return getMapper().deleteByPrimaryKey(o);
    }
    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     * @param t
     * @return
     */
    @Override
    public int delete(T t) {
        return getMapper().delete(t);
    }

    /**
     * 根据Condition条件删除数据，返回删除的条数
     * @param o
     * @return
     */
    @Override
    public int deleteByCondition(Object o) {
        return getMapper().deleteByCondition(o);
    }

    /**
     * 根据Example条件删除数据，返回删除的条数
     * @param o
     * @return
     */
    @Override
    public int deleteByExample(Object o) {
        return getMapper().deleteByExample(o);
    }

    /**
     * 根据主键@Id进行删除，多个Id以逗号,分割
     * @param s
     * @return
     */
    @Override
    public int deleteByIds(String s) {
        return getMapper().deleteByIds(s);
    }

    /**
     * @Author mgg
     * @Description 根据Condition条件更新实体`record`包含的全部属性，null值会被更新，返回更新的条数
     * @Date 15:05 2020/9/27
     * @Param [t, o]
     * @return int
     **/
    @Override
    public int updateByCondition(T record, Object condition) {
        return getMapper().updateByCondition(record,condition);
    }

    /**
     * 插入数据，限制为实体包含`id`属性并且必须为自增列，实体配置的主键策略无效
     * @param t
     * @return
     */
    @Override
    public int insertUseGeneratedKeys(T t) {
        return getMapper().insertUseGeneratedKeys(t);
    }

    /**
     * 根据Condition条件更新实体`record`包含的全部属性，null值会被更新，返回更新的条数
     * @param record
     * @param condition
     * @return
     */
    @Override
    public int updateByConditionSelective(T record, Object condition) {
        return getMapper().updateByCondition(record,condition);
    }

    /**
     * 根据Example条件更新实体`record`包含的全部属性，null值会被更新，返回更新的条数
     * @param t
     * @param o
     * @return
     */
    @Override
    public int updateByExample(T t, Object o) {
        return getMapper().updateByExample(t,o);
    }

    /**
     * 根据Example条件更新实体`record`包含的不是null的属性值，返回更新的条数
     * @param t
     * @param o
     * @return
     */
    @Override
    public int updateByExampleSelective(T t, Object o) {
        return getMapper().updateByExampleSelective(t,o);
    }

    /**
     * 根据主键更新属性不为null的值
     * @param t
     * @return
     */
    @Override
    public int updateByPrimaryKey(T t) {
        return getMapper().updateByPrimaryKey(t);
    }
    /**
     * 通过主键查询此主键是否存在
     * @param o
     * @return
     */
    @Override
    public boolean existsWithPrimaryKey(Object o) {
        return getMapper().existsWithPrimaryKey(o);
    }

    /**
     * 根据主键更新属性不为null的值
     * @param t
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(T t) {
        return getMapper().updateByPrimaryKeySelective(t);
    }
    /**
     * @Author mgg
     * @Description 分页查询
     * @Date 15:16 2020/9/27
     * @Param [query]
     * @return com.fei2e.getlost.entity.Page<T>
     **/
    public Page<T> selectByPage(Page<T> query) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Example example = new Example(clazz);
        if (query.getT()!=null) {
            Example.Criteria criteria = example.createCriteria();
            if(query.getOrderBy()!=null&& StringUtil.isNotEmpty(query.getOrderBy())){
                String orderBy=" ASC";
                if(query.isSortBy()){
                    orderBy=" DESC";
                }
                example.setOrderByClause(query.getOrderBy()+orderBy);
            }
            if(query.getStartTime()!=null){
                criteria.andBetween("create_time",query.getStartTime(),query.getEndTime());
            }
        }
        com.github.pagehelper.Page result = PageHelper.startPage(query.getPageSize(), query.getLimit());
        List<T> list = getMapper().selectByExample(example);
        query.setTotal(result.getTotal());
        query.setData(list);
        return query;
    }
}
