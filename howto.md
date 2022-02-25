1) Делаю фетч проекта KotlinAsFirst2020

2) Клонирую себе на компьютер

	$ git clone https://github.com/FAbrickA/KotlinAsFirst2020
	Cloning into 'KotlinAsFirst2020'...
	remote: Enumerating objects: 4851, done.
	remote: Total 4851 (delta 0), reused 0 (delta 0), pack-reused 4851
	Receiving objects: 100% (4851/4851), 962.22 KiB | 155.00 KiB/s, done.
	Resolving deltas: 100% (2334/2334), done.

3) Добавляю upstream-my

	$ git remote add upstream-my https://github.com/FAbrickA/KotlinAsFirst2021
	$ git fetch upstream-my
	remote: Enumerating objects: 415, done.
	remote: Counting objects: 100% (42/42), done.
	remote: Total 415 (delta 42), reused 42 (delta 42), pack-reused 373
	Receiving objects: 100% (415/415), 190.65 KiB | 258.00 KiB/s, done.
	Resolving deltas: 100% (188/188), completed with 19 local objects.
	From https://github.com/FAbrickA/KotlinAsFirst2021
	* [new branch]      master     -> upstream-my/master


4) Далее нахожу хеш последнего коммита до моих решений
Вот он: 1137b420cc95fa6894edad69b31e2da1bb985d1d

5) Делаю rebase upstream-my в ветку master

	$ git rebase --onto master 1137b420cc95fa6894edad69b31e2da1bb985d1d upstream-my/master
	Auto-merging src/lesson11/task1/Complex.kt
	CONFLICT (content): Merge conflict in src/lesson11/task1/Complex.kt
	error: could not apply 5c1707d... Doing lesson
	hint: Resolve all conflicts manually, mark them as resolved with
	hint: "git add/rm <conflicted_files>", then run "git rebase --continue".
	hint: You can instead skip this commit: run "git rebase --skip".
	hint: To abort and get back to the state before "git rebase", run "git rebase --abort".
	Could not apply 5c1707d... Doing lesson

Встретился с конфликтом, решаю его и продолжаю rebase

	$ git add src/lesson11/task1/Complex.kt
	$ git rebase --continue
	[detached HEAD 5e5ef9e] Doing lesson
	 Author: Shilonosov Alexandr <schilonosov.alex@yandex.ru>
	 1 file changed, 37 insertions(+), 8 deletions(-)
	Successfully rebased and updated detached HEAD.

6) Создаю ветку backport и загружаю в неё необходимые коммиты

	$ git branch backport
	$ git checkout master
	Previous HEAD position was 85e49d3 Fixed problems 3
	Switched to branch 'master'
	Your branch is up to date with 'origin/master'.
	$ git merge backport
	Updating d535f35..85e49d3
	Fast-forward
	 src/lesson1/task1/Simple.kt                   |  29 ++-
	 src/lesson10/task1/Regex.kt                   |  24 ++-
	 src/lesson10/task2/Html.kt                    |  62 ++++++
	 src/lesson11/task1/Complex.kt                 |  51 ++++-
	 src/lesson11/task1/FixedPointNumber.kt        | 172 +++++++++++++--
	 src/lesson11/task1/Polynom.kt                 |  89 +++++++-
	 src/lesson11/task1/UnsignedBigInteger.kt      | 168 ++++++++++++++-
	 src/lesson12/task1/OpenHashSet.kt             |  33 ++-
	 src/lesson2/task1/IfElse.kt                   |  78 ++++++-
	 src/lesson2/task2/Logical.kt                  |  42 +++-
	 src/lesson3/task1/Loop.kt                     | 177 ++++++++++++++--
	 src/lesson4/task1/List.kt                     | 163 +++++++++++++--
	 src/lesson5/task1/Map.kt                      | 120 +++++++++--
	 src/lesson6/task1/Parse.kt                    | 287 +++++++++++++++++++++++++-
	 src/lesson7/task1/Files.kt                    | 272 +++++++++++++++++++++++-
	 src/lesson8/task1/Geometry.kt                 |  44 +++-
	 src/lesson8/task1/Hex.kt                      | 142 ++++++++++++-
	 src/lesson8/task2/Chess.kt                    |  94 ++++++++-
	 src/lesson9/task1/Matrix.kt                   |  38 +++-
	 src/lesson9/task2/Matrices.kt                 | 268 ++++++++++++++++++++++--
	 test/lesson11/task1/UnsignedBigIntegerTest.kt |   8 +-
	 21 files changed, 2169 insertions(+), 192 deletions(-)

7) Добавляю upstream-theirs и делаю её merge с веткой master

	$ git remote add upstream-theirs https://github.com/hvorostovsa/KotlinAsFirst2021
	$ git fetch
	$ git merge -s ours upstream-theirs/master
	Merge made by the 'ours' strategy.

8) Создаю файл remotes и добавляю в гит

	$ git remote -v > remotes
	$ git add remotes
	$ git commit -m "Add remotes file"

9) Создаю файл howto.md и добавляю в гит

	$ touch howto.md
	$ git add howto.md
	$ git commit -m "Add howto.md"

10) Загружаю всё на гитхаб

	$ git push
	$ git checkout backport
	$ git push --set-upstream origin backport
