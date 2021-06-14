pidMinhShop="$(pgrep -f minh-shop-0.0.1-SNAPSHOT.jar)"
if [ "${pidMinhShop}" != "" ]; then
  kill -9 "$pidMinhShop"
fi
nohup java -jar /root/minh-shop/minh-shop-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod >/root/minh-shop/minh-shop.log & # no create file nohup.out
#nohup java -jar /root/minh-shop/minh-shop-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod >/dev/null 2>&1 &# no create file nohup.out
echo "deploy success"

tail -f /root/minh-shop/minh-shop.log
