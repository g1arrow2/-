package practice6;

import java.util.regex.Pattern;

public class PhoneQueryOperators {

	    /**
	     * �й��ƶ���������
	     * 139��138��137��136��135��134��147��150��151��152��157��158��159��178��182��183��184��187��188��198��195
	     * ������Ӫ�̺Ŷ�: 1703��1705��1706��165
	     **/
	    private static final String MOBILE_PATTERN = "(^1(3[4-9]|47|5[0-27-9]|65|78|8[2-478]|98)\\d{8}$)|(^170[356]\\d{7}$)";

	    /**
	     * �й����ź�������
	     * 133��149��153��173��177��180��181��189��199��191
	     * ������Ӫ�̺Ŷ�: 162��1700��1701��1702
	     **/
	    private static final String TELECOM_PATTERN = "(^1(33|49|53|62|7[37]|8[019]|9[19])\\d{8}$)|(^170[012]\\d{7}$)";

	    /**
	     * �й���ͨ��������
	     * 130��131��132��155��156��185��186��145��175��176��166��140
	     * ������Ӫ�̺Ŷ�: 171��1707��1708��1709��167
	     **/
	    private static final String UNICOM_PATTERN = "(^1(3[0-2]|4[05]|5[56]|6[67]|7[156]|8[56])\\d{8}$)|(^170[7-9]\\d{7}$)";

	    //�ƶ�
	    private static final String MOBILE = "�й��ƶ�--zz";
	    //����
	    private static final String TELECOM = "�й�����--ll";
	    //��ͨ
	    private static final String UNICOM = "�й���ͨ--jj";

	    public static String checkOperator(String phone) {
	   
	        if (Pattern.matches(MOBILE_PATTERN, phone)) {
	            return MOBILE;
	        } else if (Pattern.matches(TELECOM_PATTERN, phone)) {
	            return TELECOM;
	        } else if (Pattern.matches(UNICOM_PATTERN, phone)) {
	            return UNICOM;
	        } else {
	            return "����ĺ��벻��ȷ���ʽ����";
	        }
	    }
	}

