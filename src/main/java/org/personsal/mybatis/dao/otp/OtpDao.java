package org.personsal.mybatis.dao.otp;

import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.personsal.mybatis.entity.Otp;

/** created by: maharjananil created on: 11/26/2024 */
@Mapper
public interface OtpDao {
  void insert(Otp otp);

  void getOtp(Otp otp);

  Optional<Otp> findOne(OtpFilter filter);

  void update(Otp otp);
}
