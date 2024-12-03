package org.personsal.mybatis.common.page;

import java.util.Collections;
import java.util.List;
import lombok.Data;

/** created by: maharjananil created on: 11/15/2024 */
@Data
public class CustomPage<T> {
  private List<T> data;
  private int total;
  private int pageSize;
  private int currentPage;

  public CustomPage<T> toCustomPage(List<T> data, int total, int pageSize, int currentPage) {
    CustomPage<T> customPage = new CustomPage<>();
    customPage.setData(data.isEmpty() ? Collections.emptyList() : List.copyOf(data));
    customPage.setPageSize(pageSize);
    customPage.setCurrentPage(currentPage);
    customPage.setTotal(total);
    return customPage;
  }
}
