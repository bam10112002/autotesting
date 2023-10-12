#!/bin/bash

# Указываем директорию и префикс файлов
directory="./your_directory"
prefix="in_"

# Проверяем, существует ли директория
if [ ! -d "$directory" ]; then
  echo "Директория $directory не существует."
  exit 1
fi

# Преобразование
output_string="${input_string/in_/out_}"

# Проходим по файлам с указанным префиксом
for file in "$directory/$prefix"*; do
  if [ -f "$file" ]; then
    # Выполняем команду для каждого файла
    cat "$file" >> ./program >> out.txt
    if diff out.txt "${$file/in_/out_}" &> /dev/null; then
      echo "OK"
    else
      echo "WA in $file"
    fi
  fi
done