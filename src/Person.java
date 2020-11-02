// ����ó ������Ʈ-��� Ŭ����
import java.io.*;

public class Person implements Serializable // ����ȭ ����
{
	private String name; // �̸�
	private String phoneNum; // ��ȭ��ȣ
	private String birthDate; // �������
	private String address; // �ּ�
	private String email; // �̸���

	public Person(String name, String phoneNum, String birthDate, String address, String email) // �̸�, ��ȭ��ȣ, �������, �ּ�, �̸����� �Ķ���ͷ� �޴� ������
	{
		this.name = name;
		this.phoneNum = phoneNum;
		this.birthDate = birthDate;
		this.address = address;
		this.email = email;
	}
	
	public Person() {
		this.name=null;
		this.phoneNum = null;
		this.birthDate = null;
		this.address = null;
		this.email = null;
	}

	public String getName() // �̸� ������
	{
		return name;
	}
	public String getPhoneNum() // ��ȭ��ȣ ������
	{
		return phoneNum;
	}
	public String getBirthDate() // ������� ������
	{
		return birthDate;
	}
	public String getAddress() // �ּ� ������
	{
		return address;
	}
	public String getEmail() // �̸��� ������
	{
		return email;
	}

	public void setName(String name) // �̸� ������
	{
		this.name = name;
	}
	public void setPhoneNum(String phoneNum) // ��ȭ��ȣ ������
	{
		this.phoneNum = phoneNum;
	}
	public void setBirthDate(String birthDate) // ������� ������
	{
		this.birthDate = birthDate;
	}
	public void setAddress(String address) // �ּ� ������
	{
		this.address = address;
	}
	public void setEmail(String email) // �̸��� ������
	{
		this.email = email;
	}
	public String toString() // �̸�, ��ȭ��ȣ, �������, �ּ�, �̸���
	{
		return " | �̸�: " + name + " | ��ȭ��ȣ: " + phoneNum + " | �������: " + 
		birthDate + " | �ּ�: " + address + " | �̸���: " + email + " |";
	}
	
	// �Ʒ��� �� �Լ��� ������ ������ �ʽ��ϴ�.
	public void writeMyField(DataOutputStream out) throws Exception
	{
		try
		{
			out.writeUTF(name); // �̸� ����
			out.writeUTF(phoneNum); // ��ȭ��ȣ ����
			out.writeUTF(birthDate); // ���� ����
			out.writeUTF(address); // �ּ� ����
			out.writeUTF(email); // �̸��� ����
		}
		catch (IOException ioe) 
		{
			throw new IOException("PersonSaveIOE\"");
		}
		catch(Exception ex) 
		{
			throw new Exception("PersonSaveEx");
		}
	}
	public void readMyField(DataInputStream in) throws Exception
	{
		try
		{
			name = in.readUTF(); // �̸� �б�
			phoneNum = in.readUTF(); // ��ȭ��ȣ �б�
			birthDate = in.readUTF(); // ���� �б�
			address = in.readUTF(); // �ּ� �б�
			email = in.readUTF(); // �̸��� �б�
		}
		catch (EOFException eofe) 
		{
			throw new EOFException("PersonReadEOFE");
		}
		catch (IOException ioe)
		{
			throw new IOException("PersonReadIOE");
		}
		catch(Exception ex) 
		{
			throw new Exception("PersonReadEx");
		}
		
	}
}
