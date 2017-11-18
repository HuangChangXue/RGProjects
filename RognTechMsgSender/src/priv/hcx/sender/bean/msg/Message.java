package priv.hcx.sender.bean.msg;

public class Message {
	private MsgBody body;
	private MsgHead head;
	private MsgTail tail;

	public MsgBody getBody() {
		return body;
	}

	public void setBody(MsgBody body) {
		this.body = body;
	}

	public MsgHead getHead() {
		return head;
	}

	public void setHead(MsgHead head) {
		this.head = head;
	}

	public MsgTail getTail() {
		return tail;
	}

	public void setTail(MsgTail tail) {
		this.tail = tail;
	}

}
