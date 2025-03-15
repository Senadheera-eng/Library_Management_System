public class Member {
    private int memberId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Member(int memberId, String firstName, String lastName, String phone, String address) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return memberId + " - " + getFullName();
    }
}
