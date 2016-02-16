/**
 * 
 */
package com.jinjiang.hotel.validators;

import org.zkoss.bind.ValidationContext;
import org.zkoss.util.resource.Labels;

/**
 * @author gaojianm
 *
 */
public class EmailValidator extends GenericStringValidator {
	private String regex="(?i)\\b[\\w.%-]+@[\\w-]+(\\.[a-z]{2,4})+\\b";
	/**
	 * 
	 */
	public EmailValidator() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.validators.GenericStringValidator#validate(org.zkoss.bind.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext ctx) {
		super.validate(ctx);
		if(!isValid) return;
		if (!value.matches(regex)) {
			addInvalidMessage(ctx, Labels.getLabel("msg.invalid.email.address"));
		}
	}


}
