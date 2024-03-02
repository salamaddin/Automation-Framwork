package Lib.Files;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class FilloXL {
	public Recordset rs = null;
	public String xlPath, sheet, where;
	int rowCnt = 0, currRow = 0;

	public Recordset getRecords(String xlPath, String sheet, String cols, String where) throws Exception {
		Fillo fillo = new Fillo();
		Connection con = null;
		this.xlPath = xlPath;
		this.sheet = sheet;
		this.where = where;

		con = fillo.getConnection(xlPath);
		String qry = "Select " + cols + " from " + sheet;
		if (!"".equals(where)) {
			qry = qry + " where " + where;
		}
		try {
			rs = con.executeQuery(qry);
		} catch (Exception e) {

		}

		if (rs != null)
			rowCnt = rs.getCount();

		return rs;
	}

	public Recordset getRecords(String xlPath, String sheet, String where) throws Exception {
		return getRecords(xlPath, sheet, "*", where);
	}

	public Recordset getRecords(String xlPath, String sheet) throws Exception {
		return getRecords(xlPath, sheet, "");
	}

	public boolean next() throws Exception {
		return rs != null && rs.getCount() > 0 && rs.next();
	}

	public void first() throws Exception {
		rs.moveFirst();
		currRow = 0;
	}

	public String get(int row, String fieldName) throws Exception {
		String ret = "";
		if (row < rowCnt) {
			if (row == currRow) {
			} else if (row == currRow + 1) {
				if (rs.hasNext()) {
					rs.next();
					currRow++;
				}
			} else if (row == currRow - 1) {
				rs.movePrevious();
				currRow--;
			}
			ret = rs.getField(fieldName);
		}
		return ret;
	}

	public String get(int index) throws Exception {
		String ret = "";
		try {
			ret = rs.getField(index).value();
		} catch (Exception e) {
		}

		return ret;
	}

	public String get(String fieldName) throws Exception {
		String ret = "";
		try {
			ret = rs.getField(fieldName);
		} catch (Exception e) {
		}

		return ret;
	}

	public int getInt(String fieldName) throws Exception {
		int ret = 0;
		try {
			ret = Integer.parseInt(get(fieldName));
		} catch (Exception e) {
		}
		return ret;
	}

	public int getInt(int index) throws Exception {
		int ret = 0;
		try {
			ret = Integer.parseInt(get(index));
		} catch (Exception e) {
		}
		return ret;
	}

	public int getColCount() throws Exception {
		return rs.getFieldNames().size();
	}

	public void close() throws Exception {
		try {
			rs.close();
		} catch (Exception e) {
		}
	}

	public int getRowCount() throws Exception {
		if (rs != null)
			rowCnt = rs.getCount();
		return rowCnt;
	}

	public String getData(String xlPath, String sheetName, String colName, String where) throws Exception {
		String ret = "";
		try {
			getRecords(xlPath, sheetName, colName, where);
			if (rs.next())
				ret = rs.getField(colName);
		} catch (Exception e) {
		}
		return ret;
	}

}
