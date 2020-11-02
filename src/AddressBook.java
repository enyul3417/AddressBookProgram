// ȭ������ 2017111701 ������
// ����ó ������Ʈ - �ּҷ� Ŭ����
import java.io.*;
import java.util.*;

public class AddressBook
{
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	ArrayList<Person> list; // ArrayList ��ü ����
	private int find = 0; // ã�� ����� ��(���� �̸��� ���� ������� ���� ��� 2 �̻�)
	private int index = 0; // �迭�� �ε���

	/*
	 * public AddressBook(int num) throws Exception {//UI���� ������ ����� �� �����ͼ� ��ü ����
	 * list = new ArrayList<Person>(num); }
	 */
	
	public AddressBook(int num, ObjectInputStream in) throws Exception // ������ �Լ�
	{
		this.in = in;
		list = new ArrayList<Person>(); // ArrayList ��ü ����
		try
		{
			readFile(in); // ����� ����ó �о����
		}
		catch (IOException e) 
		{
			throw new IOException("IOException");
		}
		catch(NullPointerException e) 
		{
			throw new IOException("NullPointerException");
		}
	}

	public int getFind() // find ������
	{
		return find;
	}
	
	public void setIndex(int index) // �ε��� ������
	{
		this.index = index;
	}
	
	public int duplicateCount(String name) throws Exception // �ش� �̸��� �� ���� �ִ°� Ȯ��
	{
		find = 0;
		int count = list.size();
		
		for (int i = 0; i < count; i++)
		{
			if(name.equals(list.get(i).getName()))
				find++;
		}
		
		if (find == 0) // ���� �ƹ��͵� ã�� ���ߴٸ� �˸���
			throw new Exception("����: �ش��ϴ� �̸��� �����ϴ�.");
		
		return find;
	}
	
	public boolean checkPhoneNum(String phoneNum) //��ϵ� ��ȭ��ȣ�� �ִ��� Ȯ��
	{ 
		int count = list.size();
	
		for (int i = 0; i < count; i++)
		{
			if(phoneNum.equals(list.get(i).getPhoneNum())) //��ϵ� ��ȭ��ȣ�� ���� �� true ����
				return true;
		}
		
		return false;
		
	}	
	
	public int searchName(String name) // �̸��� �̿��� �˻�
	{
		for (int i = index; i < list.size(); i++) // �ε������� ��ü �ο�������
		{
			if(name.equals(list.get(i).getName())) // �ش� �̸��� ã����
			{
				index = i; // index�� �ش��ϴ� �ε��� i�� �ֱ�
				break; // ã���� �ݺ��� Ż��
			}
		}
		return index;	
	}
	
	public int searchNum(String phoneNum) throws Exception // ��ȭ��ȣ�� �̿��� �˻�
	{
		index = -1; // �ε����� �迭 ��(-1)�� ����Ŵ
		
		for (int i = 0; i < list.size(); i++) // 0���� ��ü �ο�������
		{
			if(phoneNum.equals(list.get(i).getPhoneNum())) // ã������ ��ȣ�� �ִٸ�
				index = i; // index�� �ش��ϴ� �ε��� i�� �ֱ�.
		}
		
		if (index == -1) // �ε����� �迭 ��(-1)�� ����Ų�ٸ� �ƹ��͵� ã�� ���� ��.
			throw new Exception("����: �ش��ϴ� ��ȭ��ȣ�� �����ϴ�.");
		
		return index;
	}
	
	public void add(Person p)
	{
		list.add(p); // person�� �߰�
	}
	
	/*
	 * ����ó�� �־��� ��ȣ�� �˻��� �ϴ� ���̱⿡
	 * ����ó ����, ���� �Լ���
	 * ��ȣ�� �߸� �Է��� ��쿡 ���ؼ� 
	 * �ͼ����� �߻��մϴ�.
	 */
	public void modify(int index, Person p) throws Exception // �˻����� �˰Ե� �ε����� ����ó ����
	{
		if (list.size() == 0) // ��ϵ� ����ó�� �ƹ��͵� ���ٸ� �˸���
			throw new Exception("����: ��ϵ� ����ó�� �����ϴ�.");
		
		if (index < list.size()) // �ε����� ��ü �ο��� ���� ������ Ȯ��
		{
			if (index >= 0) // �迭�� �ش� �ε��� �κ� ����
			{
				list.set(index, p);
			}
			else // �ε����� 0���� ������ �ͼ��� �߻�
				throw new Exception("����: ��ȣ�� 0���� �������� �����ϴ�.");
		}
		else // ��ü �ο��� ���� �ε����� ũ�� �ͼ��� �߻�
		{
			throw new Exception("����: �Է��Ͻ� ��ȣ�� ��ϵ� �ο� �� ���� Ů�ϴ�.");	
		}
	}
	
	public void delete(int index) throws Exception // �˻����� �˰Ե� �ε����� ����ó ����
	{
		if (list.size() == 0) // ��ϵ� ����ó�� �ƹ��͵� ���ٸ� �˸���
			throw new Exception("����: ��ϵ� ����ó�� �����ϴ�.");
		
		if (index < list.size()) // �ε����� ��ü �ο��� ���� ������ Ȯ��
		{
			if (index >= 0) // �ε����� 0���� ���ų� ū�� Ȯ��
			{
				list.remove(index);
			}
			else // �ε����� 0���� ������ �ͼ��� �߻�
				throw new Exception("����: ��ȣ�� 0���� �������� �����ϴ�.");
		}
		else // ��ü �ο��� ���� �ε����� ũ�� �ͼ��� �߻�
			throw new Exception("����: �Է��Ͻ� ��ȣ�� ��ϵ� �ο� �� ���� Ů�ϴ�.");		
	}
	
	public void writeFile(ObjectOutputStream out) throws Exception
	{
		int count = list.size();
		out.writeInt(count); // ��ϵ� ����� ����
		
		for(int i = 0; i < list.size(); i++) // 0���� ��ü ���������
		{
			out.writeObject(list.get(i)); // ����ó�� �������� �����ϱ�
		}
	}
	
	public void readFile(ObjectInputStream in) throws Exception
	{

		int count = in.readInt(); // ����� ���� �б�
		Person p; // Person ��ü ����

		for (int i = 0; i < count; i++)
		{
			p = (Person) in.readObject(); // ��ü ������ȭ
			list.add(p); // person �迭�� ���ʴ�� �Է�
		}
	}
}