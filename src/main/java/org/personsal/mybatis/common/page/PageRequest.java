package org.personsal.mybatis.common.page;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;
import org.personsal.mybatis.common.enums.SortOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/** created by: maharjananil created on: 11/15/2024 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest implements Pageable, Serializable {
  private int pageNumber;
  private int pageSize;
  private Sort sort;
  private long offset;
  private String sortField;
  private SortOrder sortOrder;

  @Accessors(fluent = true)
  private boolean hasPrevious;

  @Override
  public int getPageNumber() {
    return Math.max(pageNumber, 0);
  }

  @Override
  public int getPageSize() {
    return Math.max(pageSize, 0);
  }

  @Override
  public long getOffset() {
    return Math.max(pageNumber*pageSize, 0);
  }

  @Override
  public Sort getSort() {
    if(sortOrder== SortOrder.DESC) return Sort.by(Sort.Order.desc(sortField));
    else if (sortOrder==SortOrder.ASC) return Sort.by(Sort.Direction.ASC,sortField);
    return Sort.by(Sort.Direction.DESC,sortField);
  }

  @Override
  public Pageable next() {
    return null;
  }

  @Override
  public Pageable previousOrFirst() {
    return null;
  }

  @Override
  public Pageable first() {
    return null;
  }

  @Override
  public Pageable withPage(int pageNumber) {
    return null;
  }

  @Override
  public boolean hasPrevious() {
    return false;
  }

  public PageRequest basePageRequest(SearchRequest searchRequest) {
     return PageRequest.builder()
            .pageNumber(searchRequest.getPageNumber() - 1)
            .pageSize(searchRequest.getPageSize())
            .sortField(searchRequest.getSortField())
            .sortOrder(searchRequest.getSortOrder())
            .build();
  }
}
