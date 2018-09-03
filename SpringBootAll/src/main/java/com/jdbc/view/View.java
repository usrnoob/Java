package com.jdbc.view;


import com.jdbc.action.GoddessAction;
import com.jdbc.model.Goddess;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class View {
    private static final String CONTEXT = "��ӭ����Ů�������\n" +
            "������Ů������Ĺ����б�\n" +
            "[MAIN/M]:���˵�\n" +
            "[QUERY/Q]:�鿴ȫ��Ů�����Ϣ\n" +
            "[GET/G]:�鿴ĳλŮ�����ϸ��Ϣ\n" +
            "[ADD/A]:���Ů����Ϣ\n" +
            "[UPDATE/U]:����Ů����Ϣ\n" +
            "[DELETE/D]:ɾ��Ů����Ϣ\n" +
            "[SEARCH/S]:��ѯŮ����Ϣ(�����������ֻ�������ѯ)\n" +
            "[EXIT/E]:�˳�Ů�����\n" +
            "[BREAK/B]:�˳���ǰ���ܣ��������˵�";

    private static final String OPERATION_MAIN = "MAIN";
    private static final String OPERATION_QUERY = "QUERY";
    private static final String OPERATION_GET = "GET";
    private static final String OPERATION_ADD = "ADD";
    private static final String OPERATION_UPDATE = "UPDATE";
    private static final String OPERATION_DELETE = "DELETE";
    private static final String OPERATION_SEARCH = "SEARCH";
    private static final String OPERATION_EXIT = "EXIT";
    private static final String OPERATION_BREAK = "BREAK";


    public static void main(String[] args) throws SQLException {
        GoddessAction act = new GoddessAction();


        System.out.println(CONTEXT);
        Scanner scan = new Scanner(System.in);
        String OPERATION = "";
        String[] str = {"m", "q", "g", "a", "u", "d", "s", "e", "b"};
        List<String> option = new ArrayList<String>();
        for (int i = 0; i < str.length; i++) {
            option.add(str[i]);
        }

        while (true){
        System.out.println("�����������");
        OPERATION = scan.next();

        if (!option.contains(OPERATION)) {
            System.out.println("���벻�Ϸ�������������: ");
            OPERATION = scan.next();
        }

        if (OPERATION.toLowerCase().equals("m")) {
            System.out.println(CONTEXT);
        } else if (OPERATION.toLowerCase().equals("e")) {
            System.out.println("���ѳɹ��˳�");
            System.exit(0);
        } else if (OPERATION.toLowerCase().equals("q")) {
            for (Goddess g : act.query()) {
                System.out.println(g.getUser_name());
            }
        } else if (OPERATION.toLowerCase().equals("g")) {
            System.out.println("������Ҫ��ѯ��Ů��ID�� ");
            int id = scan.nextInt();
            System.out.println(act.get(id).getUser_name());
        } else if (OPERATION.toLowerCase().equals("a")) {
            Goddess goddess = new Goddess();
            String pr = OPERATION.toLowerCase();
            int step = 1;
            while (step<7){
                if (step == 1) {
                    System.out.println("������Ҫ��ӵ�Ů�������� ");
                    goddess.setUser_name(scan.next());
                } else if (step == 2) {
                    System.out.println("������Ҫ��ӵ�Ů�����䣺 ");
                    try {
                        scan = new Scanner(System.in);
                        goddess.setAge(scan.nextInt());
                    }catch (InputMismatchException e){
                        e.printStackTrace();
                        System.out.println("������ĸ�ʽ��������������");
                        step=2-1;
                    }

                } else if (step == 3) {
                    System.out.println("������Ҫ��ӵ�Ů�����գ� ");
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                    Date birthday = null;
                    try {
                        scan = new Scanner(System.in);
                        birthday = sf.parse(scan.next());
                        goddess.setBirthday(birthday);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        System.out.println("������ĸ�ʽ��������������");
                        step=3-1;
                    }
                } else if (step == 4) {
                    System.out.println("������Ҫ��ӵ�Ů��email�� ");
                    goddess.setEmail(scan.next());
                } else if (step == 5) {
                    System.out.println("������Ҫ��ӵ�Ů��绰�� ");
                    goddess.setMobile(scan.next());
                } else if (step == 6) {
                    try {
                        act.add(goddess);
                        System.out.println("�ɹ����Ů�� " + goddess.getUser_name());
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("���Ů��ʧ��");
                    }
                }
                step++;
            }


        }
        }


    }
}
