# Tanzaku app

# IOS
Current Version App: 1.0.2
  + Build number 4: Cả công ty và khách hàng đang test (`master`)
    - Payment
    - Xcode 10, expo 32, built with ios SDK > 12.1, target: ios 10
#### 24/04/2019
  + Build number 5: Chỉ công ty đang test (`master`)
    - Thêm phần splash screen
  + Build number 6: Khách hàng test (được build trên nhánh `staging-client`)
    - Thêm phần splash screen
    - Dùng domain dành riêng cho khách hàng test


    yarn & npx jetify

# Android
#### 29/11/2021 Build Debug
  1. Open VSCode: yarn install
  2. cd ios && pod install
  3. Mở thư mục android bằng Android Studio
  4. Tải NDK bản 20.1.5948944 :  
      Tools => SDK Manager => Chọn Tab SDK Tools => Tích vào Show package Detail => tải phiên bản NDK 20.1.5948944 
  5. Nếu build Debug quay lại VSCode chạy:  expo start, build release thì bỏ qua bước này.
  6. Build Project Bằng Android studio. Nếu lỗi Expo Player thì chọn "Disable Grade 'offline mode' and sync project". Sau đó build lại

#### 14/12/2021 Build Release
Lưu ý trước khi build:
Mở VSCode, chạy: expo publish