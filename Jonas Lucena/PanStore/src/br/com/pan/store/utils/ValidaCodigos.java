package br.com.pan.store.utils;

public class ValidaCodigos {
	
	   public static boolean validaCodigoPagamento(String codigoForma) {
		   if (codigoForma.equals("1")) {
			   return true;
		   } else if (codigoForma.equals("2")) {
			   return true;
		   } else if (codigoForma.equals("3")) {
			   return true;
		   } else if (codigoForma.equals("4")) {
			   return true;
		   }
		   return false;
	   }
	   
	   public static boolean validaCodigoProduto(String codigoProduto) {
		   if (codigoProduto.equals("1")) {
			   return true;
		   } else if (codigoProduto.equals("2")) {
			   return true;
		   } else if (codigoProduto.equals("3")) {
			   return true;
		   } else if (codigoProduto.equals("4")) {
			   return true;
		   } else if (codigoProduto.equals("5")) {
			   return true;
		   } else if (codigoProduto.equals("6")) {
			   return true;
		   } else if (codigoProduto.equals("7")) {
			   return true;
		   } else if (codigoProduto.equals("8")) {
			   return true;
		   } else if (codigoProduto.equals("9")) {
			   return true;
		   } else if (codigoProduto.equals("10")) {
			   return true;
		   }
		   return false;
	   }
}
