package br.com.kebase.dbUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class DbUtil {
	
	public static java.util.Date getJavaDate(ResultSet result, String nomeCampo) throws SQLException {
		Date dataFalecimento = result.getDate(nomeCampo);
		java.util.Date javaDate = null;
		if (dataFalecimento != null) {
			javaDate = new Date(dataFalecimento.getTime());
		}
		return javaDate;
	}
	
	public static java.util.Date getStampDate(ResultSet result, String nomeCampo) throws SQLException {
		Date data = new Date(result.getTimestamp(nomeCampo).getTime());
		java.util.Date javaDate = null;
		if (data != null) {
			javaDate = new Date(data.getTime());
		}
		return javaDate;
	}
	
	public static java.sql.Date getSqlDate(java.util.Date date) {
		java.sql.Date sqlDate = null;
		if (date != null) {
			sqlDate = new java.sql.Date(date.getTime());
		}
		return sqlDate;
	}
	
	public static String retiraMascaras(String data){
		if(data != null && !data.equals("")){
			return data.replace(".", "").replace("-", "").replace("(", "").replace(")", "").replace("/", "").replace(" ", "")
						.replace("_", "").trim();
		}else{
			return data;
		}
	}
	
	public static java.sql.Timestamp getSqlTimestamp(java.util.Date date){
		java.sql.Timestamp sqlDate = null;
		if (date != null) {
			sqlDate = new java.sql.Timestamp(date.getTime());
		}
		return sqlDate;
	}
        
        public static byte[] getInputStream(ResultSet result, String campo) throws IOException, SQLException{
            InputStream input = result.getBinaryStream(campo);
            byte[] bytes = new byte[input.available()];
            
            return bytes;
        }
	
}
