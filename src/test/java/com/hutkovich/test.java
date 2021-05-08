package com.hutkovich;

import java.util.Locale;
import java.util.ResourceBundle;

public class test
{
  private int a;
 final public static void main(String[] args)
  {
	ResourceBundle rb = ResourceBundle.getBundle("sometext", Locale.forLanguageTag("ru-RU"));
	System.out.print(rb.getString("message"));
  }

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + a;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	test other = (test) obj;
	if (a != other.a)
		return false;
	return true;
}
}
