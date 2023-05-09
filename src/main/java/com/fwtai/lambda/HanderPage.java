package com.fwtai.lambda;

public interface HanderPage {}

//分页处理的示例代码

/*

//定义分页的实体
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

//定义函数式接口
@FunctionalInterface
public interface HanderPage<T> {
  PageInfo<T> getData();
}

@Repository层处理
public PageInfo<HashMap<String,Object>> getPageInfo(final HanderPage<HashMap<String,Object>> hander){
  return hander.getData();
}

dao层处理
public PageInfo<HashMap<String,Object>> getUsers(final Long kid){
  return dao.getPageInfo(() -> {
    final Integer total = dao.queryForInteger("api_buy.getUsersTotal",kid);
    final List<HashMap<String,Object>> listData = dao.queryForListHashMap("api_buy.getUsersData",kid);
    final PageInfo<HashMap<String,Object>> pageInfo = new PageInfo<>();
    pageInfo.setTotal(total);
    pageInfo.setData(listData);
    return pageInfo;
  });
}

xml查询
<select id="getUsersTotal" resultType="Integer" parameterType="Long">
  SELECT COUNT(1) as total FROM sys_user
</select>

<select id="getUsersData" resultType="HashMap" parameterType="Long">
  SELECT kid,user_name,type,add_date,times FROM sys_user LIMIT 0,5
</select>

service层处理
public String getUsers(final Long kid){
  final PageInfo<HashMap<String,Object>> pageInfo = apiBuyDao.getUsers(kid);
  return ToolClient.jsonPage(pageInfo.getData(),pageInfo.getTotal());
}

*/