package br.com.kebase.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

public class CalcularData {
	
	public static long ultimaHoraDia(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data); //colocando o objeto Date no Calendar
		calendar.set(Calendar.HOUR_OF_DAY, 23); //zerando as horas, minuots e segundos..
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		
		return calendar.getTimeInMillis();
		
	}
	
	public static String somarData(int dias){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, dias);
		
		return sdf.format(c.getTime());
	}
	
	public static Date somarMeses(int qtdMeses){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, qtdMeses);
		
		return c.getTime();
	}
	
	public static Date somarMeses(int qtdMeses, Date data){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.MONTH, qtdMeses);
		
		return c.getTime();
	}
	
	public static String somarData(int dias, Date data){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(new SimpleDateFormat("yyyy").format(data)), Integer.parseInt(new SimpleDateFormat("MM").format(data)), 
				Integer.parseInt(new SimpleDateFormat("dd").format(data)));
		c.add(Calendar.DAY_OF_YEAR, dias);
		
		return sdf.format(c.getTime());
	}
	
	public static Date acrescentarDias(int dias, Date data){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.DAY_OF_YEAR, dias);
		
		return c.getTime();
	}
	
	public static int TirarDiferenca(Date data1, Date data2){
		
		if(data1 == null || data2 == null){
			JOptionPane.showMessageDialog(null, "Data Inv�lida!", "Data Inv�lida!",  JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		
		if(data2 != null){
		  
	     GregorianCalendar dataInicio = new GregorianCalendar();  
	     GregorianCalendar dataFim = new GregorianCalendar();  
	       
	     GregorianCalendar curTime = new GregorianCalendar();  
	     GregorianCalendar baseTime = new GregorianCalendar();  
	  
	     dataInicio.setTime(data1);  
	     dataFim.setTime(data2);  
	       
	     int dif_multiplier = 1;  
	       
	     // Verifica a ordem de inicio das datas  
	     if( data1.compareTo( data2 ) < 0 ){  
	         baseTime.setTime(data2);  
	         curTime.setTime(data1);  
	         dif_multiplier = 1;  
	     }else{  
	         baseTime.setTime(data1);  
	         curTime.setTime(data2);  
	         dif_multiplier = -1;  
	     }  
	       
	     int result_years = 0;  
	     int result_months = 0;  
	     int result_days = 0;  
	  
	     // Para cada mes e ano, vai de mes em mes pegar o ultimo dia para import acumulando  
	     // no total de dias. Ja leva em consideracao ano bissesto  
	     while( curTime.get(GregorianCalendar.YEAR) < baseTime.get(GregorianCalendar.YEAR) ||  
	            curTime.get(GregorianCalendar.MONTH) < baseTime.get(GregorianCalendar.MONTH)  )  
	     {  
	           
	         int max_day = curTime.getActualMaximum( GregorianCalendar.DAY_OF_MONTH );  
	         result_months += max_day;  
	         curTime.add(GregorianCalendar.MONTH, 1);  
	           
	     }  
	       
	     // Marca que � um saldo negativo ou positivo  
	     result_months = result_months*dif_multiplier;  
	       
	       
	     // Retirna a diferenca de dias do total dos meses  
	     result_days += (dataFim.get(GregorianCalendar.DAY_OF_MONTH) - dataInicio.get(GregorianCalendar.DAY_OF_MONTH));  
	       
	     return result_years+result_months+result_days;
		}else {
			return 0;
		}
	}  
	
	public static boolean compararDataMenor(Date dt1, Date dt2) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String data1 = sdf.format(dt1);
		String data2 = sdf.format(dt2);
		if(data1.equals(data2)) {
			return false;
		}else if (dt1.before(dt2)){
			return true;
		}
		
		return false;
	}
	
	public static int getIdade(Date dataNascimento) {
		Calendar dateOfBirth = new GregorianCalendar();
		dateOfBirth.setTime(dataNascimento);

		// Cria um objeto calendar com a data atual
		Calendar today = Calendar.getInstance();

		// Obt�m a idade baseado no ano
		int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

		dateOfBirth.add(Calendar.YEAR, age);

		// se a data de hoje � antes da data de Nascimento, ent�o diminui 1(um)
		if (today.before(dateOfBirth)) {
			age--;
		}
		return age;

	}

}
