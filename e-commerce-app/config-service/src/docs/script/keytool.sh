# jks 파일 생성
$ keytool -genkeypair \
-alias apiEncryptionKey \
-keyalg RSA \
-dname "CN=wooklab, OU=API Dev, O=wooklab.com, L=Seoul, C=KR" \
-keypass "test1234" \
-keystore apiEncryptionKey.jks \
-storepass "test1234"


# private key 상세내역 보기
keytool -list -keystore apiEncryptionKey.jks -v


# 인증서 파일 생성하기
keytool -export \
-alias apiEncryptionKey \
-keystore apiEncryptionKey.jks \
-rfc -file trustServer.cer          # request for comment


# 인증서 -> publicKey.jks (public key 생성)
keytool -import \
-alias trustServer \
-file trustServer.cer \
-keystore publicKey.jks


# public key 상세내역 보기
keytool -list -keystore publicKey.jks -v