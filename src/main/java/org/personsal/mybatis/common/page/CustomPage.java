package org.personsal.mybatis.common.page;

import java.util.List;
import lombok.Data;
import org.springframework.data.domain.Page;

/** created by: maharjananil created on: 11/15/2024 */
@Data
public class CustomPage<T> {
  private List<T> data;
  private int total;
  private int pageSize;
  private int currentPage;

  public CustomPage<T> toCustomPage(Page<T> data, int total, int pageSize, int currentPage) {
    CustomPage<T> customPage = new CustomPage<>();
    if (data.hasContent()) {
      customPage.setData(List.copyOf(data.getContent()));
      customPage.setPageSize(data.getPageable().getPageSize());
      customPage.setCurrentPage(data.getPageable().getPageNumber());
      customPage.setTotal(data.getTotalPages());
    }
    return customPage;
  }
}
