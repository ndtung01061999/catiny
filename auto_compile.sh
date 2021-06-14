#!/bin/bash

########################
# @author by yuvytung  #
########################

# note : to use this file in windows you must install GIT .

# declare parameters##############################################################################
delayCompile=$((2)) # pause time (seconds)
folderCheck="src/main/java"

# declare functions need to be used###############################################################
recreateSha() {
  (
    find $folderCheck -type f -print0 | sort -z | xargs -0 sha1sum
    find $folderCheck \( -type f -o -type d \) -print0 | sort -z | xargs -0 stat -c '%n %a'
  ) | sha1sum | awk '{print $1}'
}

recreateShaV2() {
  ls -alR --full-time src | sha1sum | awk '{print $1}'
}

compileJava() {
  ./gradlew compileJava -x processResources
}

now() {
  date +'%Y-%m-%d %H:%M:%S'
}

#main
#echo "script này sẽ kiểm tra mã checksum của thư mục src nếu file có thay đổi sẽ thực hiện biên dịch lại java"
echo -e "Hello.\nYou are lost in the world of Nhếch.\nLet Go! now ---> $(now)"
oldSha=$(recreateSha)
while :; do
  newSha=$(recreateSha)
  if [ "$oldSha" != "$newSha" ]; then
#    sleep $delayCompile
#    if [ "$newSha" = "$(recreateSha)" ]; then
      echo "$(now) | $newSha | recompile (only java code)"
      compileJava | tail -n 2 | head -n 1
      tput cuu 5 && tput ed
      echo "recompile done."
      oldSha=$newSha
      sleep $((delayCompile * 2))
#    else
#      echo "$(now) | is writing code so it won't recompile"
#      sleep $((delayCompile * 2))
#    fi
  else
    echo "$(now) Nhếch đang nghỉ ngơi"
    sleep $((delayCompile * 1, 5))
  fi
done

#  một ý tưởng cực hay
#  ls -alR --full-time src | sha1sum

#  checksum1=$(echo $( (
#    find src -type f -print0 | sort -z | xargs -0 sha1sum
#    find src \( -type f -o -type d \) -print0 | sort -z | xargs -0 stat -c '%n %a'
#  ) | sha1sum) | awk '{print $1}')

#a=$(echo $( (find src -type f -print0  | sort -z | xargs -0 sha1sum;  find src \( -type f -o -type d \) -print0 | sort -z |    xargs -0 stat -c '%n %a') | sha1sum )  |  awk '{print $1;}' )
#  oldSha
