#!/bin/bash

# Найти все поддиректории, содержащие файлы, начинающиеся с "test_"
find . -type f -name 'test_*.py' -exec dirname {} \; | sort -u | while read -r dir; do
    # Перейти в директорию
    cd "$dir" || exit 1

    # Выполнить тесты в текущей директории
    python3 -m unittest
    if [ $? -eq 0 ]
    then
      echo "Successfully test file"
    else
      exit 1
    fi

    # Вернуться обратно в исходную директорию
    cd - || exit 1
done
