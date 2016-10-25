package org.heyframework.test.batch;

import org.springframework.batch.item.ItemProcessor;

public class CreditBillProcessor implements ItemProcessor<CreditBill, CreditBill> {

	@Override
	public CreditBill process(CreditBill bill) throws Exception {
		bill.setName(bill.getName().replace("tom", "hey"));
		return bill;
	}

}
