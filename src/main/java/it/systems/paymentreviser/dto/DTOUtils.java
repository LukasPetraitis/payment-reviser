package it.systems.paymentreviser.dto;

import it.systems.paymentreviser.dto.PaymentDTO;
import org.apache.commons.lang3.ObjectUtils;

public class DTOUtils {
	private DTOUtils() {
	}
	
	public static boolean isPaymentDataEmpty(PaymentDTO paymentDTO) {
		return ObjectUtils.isEmpty(paymentDTO) ||
				ObjectUtils.isEmpty(paymentDTO.id()) ||
				ObjectUtils.isEmpty(paymentDTO.amount()) ||
				ObjectUtils.isEmpty(paymentDTO.paymentType());
	}
}
