/*
 * Copyright (C) 2014 Marcel Berkholz
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.berkholz.vcard2fritzXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * Mail address of the contact.
 *
 * @author Marcel Berkholz
 *
 */

/*
 * None of the fields or properties is bound to XML unless they are specifically
 * annotated with some of the JAXB annotations.
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "email")
public class EMail {

	@XmlAttribute
	private final Integer id;

	@XmlAttribute
	private final String classifier;

	@XmlValue
	private String email;

	/**
	 * Instantiate an email address (constructor).
	 */
	public EMail() {
		this.email = new String();
		this.classifier = "private";
		// there is only one mail address possible
		this.id = 0;
	}

	/**
	 * Instantiate an email address with the given email address(constructor).
	 *
	 * @param email Mail address to instantiate with.
	 */
	public EMail(String email) {
		this.email = new String();

		this.setEmail(email);

		this.classifier = "private";
		// there is only one mail address possible
		this.id = 0;
	}

	/**
	 * Checks if a mail address is set.
	 *
	 * @return Returns true if no mail address is given otherwise false.
	 */
	public boolean isEmpty() {
		return this.email.isEmpty();
	}

	/**
	 * Get the mail address.
	 *
	 * @return The mail address as String.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the mail address.
	 *
	 * @param email Mail address as String representation.
	 */
	public void setEmail(String email) {
		// we validate against the trimmed mail address
		String trimmedEmailAddress = EMail.trim(email);
		if (EMail.validateEmail(trimmedEmailAddress)) {
			this.email = trimmedEmailAddress;
		} else {
			System.out.println("Mail address \'" + email + "\' (trimmed: " + trimmedEmailAddress + ") is not valid.");
		}
	}

	/**
	 * Helperfunction to trim not allowed characters in the email address.
	 *
	 * @param email Mail address as String representation with white spaces,
	 * tabulators etc.
	 * @return Mail address with no white spaces and tabulators at the beginning
	 * and at the end.
	 */
	public static String trim(String email) {
		return email.trim().replace("\t", "");
	}

	/**
	 * Validate a mail address.
	 *
	 * @param email Mail address as String representation.
	 * @return True if mail address is valid, otherwise false.
	 */
	public static boolean validateEmail(String email) {
		if (email.isEmpty()) {
			// if no mail is in the contact, we accept an empty string
			return true;
		} else {
			// Get an EmailValidator
			EmailValidator validator = EmailValidator.getInstance();

			// Validate an email address
			return validator.isValid(email);
		}
	}
}
