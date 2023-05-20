package pageUIs;

public class HomePageUI {
	public static final String REGISTER_LINK = "//a[@class='ico-register']";
	
	//1 - public:
	// - Không set protected vì các class bên ngoài package sẽ ko dùng được -> ko áp dụng kế thừa
	// - Không set private: ngoài class không dùng được
	// - Không set default: ngoài package ko dùng được
	// public: bất kì 1 class nào cũng gọi tới để sự dụng được
	
	//2 - static: (no need to create new instance)
	// Truy cập từ phạm vi class ở 1 class khác được
	
	//3 - final: (fix value)
	// Không được phép thay đổi trá trị của biến khi sử dụng
	// static + final = Hằng số
	// Tên biết hằng số: Viết hoa, nếu nhiều hơn 1 từ thì phân tách bởi dấu "_"
	
	//4 - String: (vì tham số trong class thì nó là String)
	
	//5 - Tên biến: Tên của Element + loại element
	
}
