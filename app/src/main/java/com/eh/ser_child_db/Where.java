package com.eh.ser_child_db;

import java.util.List;


/**
 * 根据自定义searchfilter构造where语句
 *
 * Created by luojiang on 2015/9/17.
 * 
 */
public class Where
{
	public static String where(List<SearchFilter> filters)
	{
		StringBuffer wherebuffer = new StringBuffer("1=1");
		if (filters != null && filters.size() > 0)
		{
			for (SearchFilter filter : filters)
			{
				wherebuffer.append(" AND ");

				if (filter.operator.equals(SearchFilter.Operator.EQ))
				{
					dealMulitFieldName(filter, wherebuffer, " = ");
				} else if (filter.operator.equals(SearchFilter.Operator.LIKE))
				{
					if (!filter.isMuliFields())
					{
						filter.setValue("%" + filter.getValue() + "%");
					}
					dealMulitFieldName(filter, wherebuffer, " LIKE ");
				} else if (filter.operator.equals(SearchFilter.Operator.GT))
				{
					dealMulitFieldName(filter, wherebuffer, " > ");
				} else if (filter.operator.equals(SearchFilter.Operator.GTE))
				{
					dealMulitFieldName(filter, wherebuffer, " >= ");
				} else if (filter.operator.equals(SearchFilter.Operator.LT))
				{
					dealMulitFieldName(filter, wherebuffer, " < ");
				} else if (filter.operator.equals(SearchFilter.Operator.LTE))
				{
					dealMulitFieldName(filter, wherebuffer, " <= ");
				} else if (filter.operator.equals(SearchFilter.Operator.ISNULL))
				{
					wherebuffer.append(filter.getFieldName() + " is null ");
				} else if (filter.operator.equals(SearchFilter.Operator.ISNOTNULL))
				{
					wherebuffer.append(filter.getFieldName() + " is not null ");
				}
			}
		}
		return wherebuffer.toString();
	}

	// 1=1 and (filed1 = value or field2 = value)
	private static void dealMulitFieldName(SearchFilter filter, StringBuffer wherebuffer, String sign)
	{
		if (filter.isMuliFields())
		{
			wherebuffer.append("(");
			for (int i = filter.getFields().length - 1; i >= 0; i--)
			{
				wherebuffer.append(filter.fieldName + " LIKE \'%" + filter.getFields()[i] + "%\'");
				if (i != 0)
				{
					wherebuffer.append(" AND ");
				}
			}
			wherebuffer.append(")");
		} else
		{
			wherebuffer.append(filter.getFieldName() + sign + "'" + filter.getValue() + "'");
		}
	}
}
