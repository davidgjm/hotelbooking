/**
 * 
 */
package com.jinjiang.hotel.mappers;

import java.util.List;

import com.jinjiang.hotel.domain.PaymentMode;

/**
 * @author gaojianm
 *
 */
public interface PaymentModeMapper {
	Integer save(PaymentMode pm);
	void update(PaymentMode pm);
	void delete(PaymentMode pm);
	PaymentMode getById(Integer id);
	List<PaymentMode> getAll();
}
