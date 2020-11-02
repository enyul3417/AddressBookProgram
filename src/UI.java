// ����ó ������Ʈ-UIŬ����

import java.util.*;
import java.io.*;

public class UI {
	public static void main(String ars[]) throws Exception
	{
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		Person newPerson;
		AddressBook ab = null;
		
		Scanner scan = new Scanner(System.in);		
		
		int menu; // �޴� ��ȣ
		int num; // ����, ���� �� �ε����� �޴� �κ�

		try
		{
			in = new ObjectInputStream(new FileInputStream("AddressBook.dat")); // ���� ����
			ab = new AddressBook(100, in); // ��ü ����
			ab.readFile(in);
			in.close(); // in �ݱ�
		}
		catch(FileNotFoundException fnfe) // ������ ã�� ���� ���
		{
			System.out.println("������ �������� �ʽ��ϴ�.");
			ab = new AddressBook(100);
		}
		catch(EOFException eofe) // ���� �����Ͱ� ���� ���
		{
			System.out.println("��");
		}
		catch(IOException ioe) // ������ ���� �� ���� ���
		{
			System.out.println("������ ���� �� �����ϴ�.");
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println("�ش� Ŭ������ �������� �ʽ��ϴ�.");
		}
		finally
		{
			try
			{
				in.close(); // ���� �ݱ�
			}
			catch(Exception e)
			{
			}
		}
		
		while(true) // �ݺ���
		{
			// ����� �޴� ���
			System.out.println("");
			System.out.println("--------------------------*********����ó ���� ���α׷�*********------------------------");
			System.out.println("1. �� ����ó �߰�");
			System.out.println("2. ����ó �˻�");
			System.out.println("3. ��ü ����ó ��ȸ");
			System.out.println("4. ����ó ����");
			System.out.println("5. ����ó ����");
			System.out.println("6. ����ó ���� ����");
			System.out.println("7. ���α׷� ����");
			System.out.println("----------------------------------------------------------------------------------");
			System.out.print("���Ͻô� ��ȣ�� �Է����ּ���: ");
			
			try
			{
				menu = scan.nextInt(); // ��ȣ �Է�
				scan.nextLine(); // ���� ����
			}
			catch(InputMismatchException misExc) // ������ �ƴ� ���� �Է����� ��
			{
				System.out.println("�߸��� �Է��Դϴ�. 1, 2�� ���� ������ �Է����ּ���.");
				scan.nextLine(); // ���� ����
				continue;
			}
			
			if(menu == 7) // ���α׷� ����
			{
				System.out.println("���α׷��� �����մϴ�.");
				break; // �ݺ��� ����
			}
				
			switch(menu) // ��ȣ�� ���� �޼ҵ� ����
			{
			case 1: // �� ����ó �߰�
				while(true)
				{
					System.out.println("���ο� ����ó�� �߰��մϴ�.");
					
					System.out.print("�̸�: "); // �̸� �Է�
					String n = scan.next();
					scan.nextLine(); // ���� ����
					System.out.print("��ȭ��ȣ: "); // ��ȭ��ȣ �Է�
					String pn = scan.next();
					if(ab.checkPhoneNum(pn) == true) // ���� ��ϵ� ��ȭ��ȣ�� ��� 
					{
						System.out.println("�̹� ��ϵ� ��ȭ��ȣ �Դϴ�.");
						break; // �޴��� ���ư��� ��
					}
					scan.nextLine(); // ���� ����
					System.out.print("����(���Է�: 0): "); // ���� �Է�
					String bd = scan.next();
					scan.nextLine(); // ���� ����
					System.out.print("�ּ�(���Է�: 0): "); // �ּ� �Է�
					String a = scan.next();
					scan.nextLine(); // ���� ����
					System.out.print("�̸���(���Է�: 0): "); // �̸��� �Է�
					String em = scan.next();
					scan.nextLine(); // ���� ����

					newPerson = new Person(n, pn, bd, a, em); // Person��ü�� ���ο� ����ó ����
					ab.add(newPerson);
					System.out.println("����Ǿ����ϴ�.");
					
					System.out.println("����ó�� �� �߰��Ͻðڽ��ϱ�?(Y/N)");
					char as = scan.next().charAt(0);
					scan.nextLine(); // ���� ����
					
					if(as == 'N' || as == 'n') // ��ҹ��� N �Է� ��
						break; // ����ó �߰� ����
					else if (as == 'Y' || as == 'y') // ��ҹ��� Y �Է� ��
						continue;
					else // �� ���� �͵��� �Է��Ѵٸ�
						throw new Exception("�߸��Է��ϼ̽��ϴ�.");
				}
				break;
			
			case 2: // ����ó �˻�
				System.out.println("1. �̸����� �˻�");
				System.out.println("2. ��ȭ��ȣ�� �˻�");
				System.out.print("���ϴ� �׸��� ���ڸ� �Է����ּ���: ");
				
				try
				{
					menu = scan.nextInt(); // ��ȣ �Է�
					scan.nextLine(); // ���� ����
				}
				catch(InputMismatchException misExc) // ���� �ܿ� �ٸ� ���� �Է� ��
				{
					System.out.println("�߸��� �Է��Դϴ�. 1, 2�� ���� ������ �Է����ּ���.");
					scan.nextLine(); // ���� ����
					continue;
				}
				
				switch(menu)
				{
				case 1: // �̸����� �˻�
					System.out.println("�̸����� �˻��մϴ�.");
					System.out.print("�̸��� �Է����ּ���: ");
					ab.setIndex(0); // �ε����� 0���� ���� => �̸��� ã���� ó������ �˻��ؾ��ϱ� ����
					
					String n = scan.next(); // ã������ �̸� �Է�
					scan.nextLine(); // ���� ����
					try
					{
						ab.duplicateCount(n); // �ش� �̸��� ����� ������ �ִ��� Ȯ��, ��ã���� �ͼ��� �߻�
					}
					catch(Exception e)
					{
						String str = e.getMessage();
						System.err.println(str); // ���� �޽��� ���
					}
					
					for ( int i = 0; i < ab.getFind(); i++) // ã�� ��� ����ŭ ����ó ã�Ƽ� ���
					{
						int index = ab.searchName(n); // �ش� �̸� ã�� �� �ε��� �ޱ�
						// �ش� �̸��� �ִ� �ε������� +1�� �ٲٱ� => �ٲ� �ε������� ����� ������� �������� �ش� �̸� �˻� ����
						ab.setIndex(index+1);
						System.out.println("��ȣ: " + index + ab.list.get(index)); // �˻� ��� ���
					}
					
					break;
					
				case 2: // ��ȭ��ȣ�� �˻�
					System.out.println("��ȭ��ȣ�� �˻��մϴ�.");
					System.out.print("��ȭ��ȣ�� �Է����ּ���: ");
					String pn = scan.next(); // ��ȭ��ȣ �Է�
					scan.nextLine(); // ���� ����
					
					try
					{
						int index = ab.searchNum(pn); // ��ȭ��ȣ�� �˻��ϰ� �ش� �ε��� �ޱ�
						System.out.println("��ȣ: " + index + ab.list.get(index)); // �˻� ��� ���
						System.out.println("�˻��� �Ϸ�Ǿ����ϴ�.");
					}
					catch (Exception e)
					{
						String str = e.getMessage();
						System.err.println(str); // ���� �޽��� ���
					}
					break;
					
				default:
					System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է��ϼ���.");	
				}
				
				break;
			
			case 3: // ��ü ����ó ��ȸ
				System.out.println("��ü ����ó�� ��ȸ�մϴ�.");
				System.out.println("�� ����ó�� ������ " + ab.list.size() + "�� �Դϴ�."); // ����� ����ó�� ���� �˷��ֱ�
				for (int i = 0; i < ab.list.size(); i++) // 0���� ����� ����ó ����ŭ
				{
					System.out.println("��ȣ: " + i + ab.list.get(i)); // �ش� ����ó ���� ���
				}
				System.out.println("��ü ����ó ����� �Ϸ��߽��ϴ�.");
				
				break;
			
			case 4: // ����ó ����
				System.out.println("����ó�� �����մϴ�.");
				System.out.print("�����Ͻ� ����ó�� ��ȣ�� �Է����ּ���: ");
				num = scan.nextInt(); // �˻��� ���� �˾Ƴ� �ε��� �Է�
				scan.nextLine(); // ���� ����
				
				System.out.println("������ ������ �Է��մϴ�.");
				System.out.print("�̸�: "); // �̸� �Է�
				String n = scan.next();
				scan.nextLine(); // ���� ����
				
				String pn;
				do // �ٸ� ��ȣ�� �ߺ����� �ʵ��� �ϴ� �ڵ�
				{
					System.out.print("��ȭ��ȣ: "); // ��ȭ��ȣ �Է�
					pn = scan.next();
					if(pn.equals(ab.list.get(num).getPhoneNum())) // ��ȭ��ȣ�� �������� �ʴ� ���
						break;
					else
					{
						if(ab.checkPhoneNum(pn) == true) // ���� �ٸ� ��ȣ�� �ߺ��� ���
						{
							System.out.println("�̹� ��ϵ� ��ȭ��ȣ �Դϴ�.");
						}
						else
							break;
					}	
					scan.nextLine(); // ���� ����
				}while(true);
				
				System.out.print("����(���Է�: 0): "); // ���� �Է�
				String bd = scan.next();
				scan.nextLine(); // ���� ����
				System.out.print("�ּ�(���Է�: 0): "); // �ּ� �Է�
				String a = scan.next();
				scan.nextLine(); // ���� ����
				System.out.print("�̸���(���Է�: 0): "); // �̸��� �Է�
				String em = scan.next();
				scan.nextLine(); // ���� ����
				
				try
				{
					newPerson = new Person(n, pn, bd, a, em); // Person��ü�� ���ο� ����ó ����
					ab.modify(num, newPerson); // �迭�� �ش� �ε��� �κп� �ش� ���� �ֱ�
					System.out.println("�����Ǿ����ϴ�.");
				}
				catch(Exception e)
				{
					String str = e.getMessage();
					System.err.println(str); // ���� �޽��� ���
				}
				
				break;
				
			case 5: // ����ó ����
				System.out.println("����ó�� �����մϴ�.");
				System.out.print("�����Ͻ� ����ó�� ��ȣ�� �Է����ּ���: ");
				num = scan.nextInt(); // �˻��� ���� �˾Ƴ� �ε��� �Է�
				scan.nextLine(); // ���� ����
				try
				{
					ab.delete(num);
					System.out.println("������ �Ϸ�Ǿ����ϴ�.");
				}
				catch(Exception e)
				{
					String str = e.getMessage();
					System.err.println(str); // ���� �޽��� ���
				}
				break;
				
			case 6: // ����ó ���� ����
				try
				{
					out = new ObjectOutputStream(new FileOutputStream("AddressBook.dat")); // ���� ����
					ab.writeFile(out);
					System.out.println("���� ������ �Ϸ�Ǿ����ϴ�.");
				}
				catch(IOException ioe)
				{
					System.out.println("���Ϸ� ����� �� �����ϴ�.");
				}
				finally
				{
					try
					{
						out.close(); // ���� �ݱ�
					}
					catch(Exception e)
					{
					}
				}
				
				break;
				
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է��ϼ���.");
			}

		}
		scan.close(); // ��ĳ�� Ŭ���� �ݱ�
	}
}
