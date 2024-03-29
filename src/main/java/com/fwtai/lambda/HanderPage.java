package com.fwtai.lambda;

import java.util.List;

//函数式接口编程
@FunctionalInterface
public interface HanderPage<T> {
  List<T> getSelect();
}

//分页处理的示例代码

/*

@FunctionalInterface
public interface Select<E> {
  List<E> getSelect();
}

下面是实战,参考项目 wine-backend

//①定义函数式接口
@FunctionalInterface
public interface HanderPage<T> {
  PageInfo<T> getData();
}

//②定义分页的实体
public final class PageInfo<T>{

  private Integer total;

  private List<T> data;

  public Integer getTotal(){
    return total;
  }

  public void setTotal(final Integer total){
    this.total = total;
  }

  public List<T> getData(){
    return data;
  }

  public void setData(final List<T> data){
    this.data = data;
  }

}

③@Repository层处理
public <T> PageInfo<T> getPageTotal(final HanderPage<T> hander){
  return hander.getData();
}

④dao层处理
public PageInfo<HashMap<String,Object>> getUsers(final Long kid){
  return dao.getPageTotal(() -> {
    final Integer total = dao.queryForInteger("api_buy.getUsersTotal",kid);
    final List<HashMap<String,Object>> listData = dao.queryForListHashMap("api_buy.getUsersData",kid);
    final PageInfo<HashMap<String,Object>> pageInfo = new PageInfo<>();
    pageInfo.setTotal(total);
    pageInfo.setData(listData);
    return pageInfo;
  });
}

⑤xml查询
<select id="getUsersTotal" resultType="Integer" parameterType="Long">
  SELECT COUNT(1) as total FROM sys_user
</select>

<select id="getUsersData" resultType="HashMap" parameterType="Long">
  SELECT kid,user_name,type,add_date,times FROM sys_user LIMIT 0,5
</select>

⑥service层处理
public String getUsers(final Long kid){
  final PageInfo<HashMap<String,Object>> pageInfo = apiBuyDao.getUsers(kid);
  return ToolClient.jsonPage(pageInfo.getData(),pageInfo.getTotal());
}

*/