# introsofteng
Teaching material for "Introduction to Software Engineering" at Lund University, LTH. http://cs.lth.se/etsa02/

## Contents of this repo

The main directories are:
* `exercises` with course material for the exercise sessions
* `labs` with instructions and source code for the lab sessions
* `lectures` with slides
* `project-rumble` with instructions, templates, and examples for the Robocode project

## How to contribute to this repo

### Fork and clone

* Learn the basics about git, especially the "Getting Started" and "Git Basics" sections in this book: https://git-scm.com/book/en/v2

* Get a GitHub account if you don't have one already. Recommended user name if in doubt: `firstnamefamilyname` with no capital letters and no hyphens.

* Install git: https://git-scm.com/book/en/v2/Getting-Started-Installing-Git

* Make a **fork** of lunduniversity/introsofteng in GitHub to your own GitHub account: https://help.github.com/articles/fork-a-repo/

* **Clone** your fork to your local computer: https://help.github.com/articles/cloning-a-repository/

### Keeping your fork in synch

* If you install the GitHub client (avaliable for Win and Mac but not Linux) called "GitHub desktop" https://desktop.github.com/ you can keep your fork in synch with the upstream repo by a single click in the GUI.

* Otherwise, this is how to pull changes from upstream to your fork with git commands: https://help.github.com/articles/syncing-a-fork/

### Making contributions

* If you find a typo or minor issue that is straight-forward to fix you are very welcome to create a pull request directly as explained below. But if your contribution is more significant you should open an issue first and start a discussion about your proposal. In the latter case, click the issue tab at the top of this page.

* Before you change locally, make sure your fork is in synch (see above). Frequently do `git pull` or press the synch button in the GitHub desktop GUI.

* You must check that your fix compiles (to LaTeX or bytecode) before you commit.

* Whenever you are ready with an incremental change, run `git commit -m "msg"` and then `git push`, or commit in the GUI and press the synch button. Write a useful commit message.

* When you are ready with a contribution that is good enough to be incorporated in upstream, then create a pull request: https://help.github.com/articles/creating-a-pull-request/

* Keep your pull requests minimal and coherent to create a small change sets that will be easy to merge as a single unit. Don't pack a lot of unrelated changes in the same pull request.

* Don't include pdf:s or binaries in the pull request. The maintainers will recompile the repo after your pull request has been merged. You can then checkout your pdf:s before you synch with upstream.

## License

Copyright © 2018-2019. Dept. of Computer Science at Lund University, Lund, Sweden.

This work is licensed under a Creative Commons Attribution-ShareAlike 4.0 International License.

You are free to:

    Share — copy and redistribute the material in any medium or format
    Adapt — remix, transform, and build upon the materia for any purpose, even commercially.
    The licensor cannot revoke these freedoms as long as you follow the license terms.

Under the following terms:

    Attribution — You must give appropriate credit, provide a link to the license, and indicate if changes were made. You may do so in any reasonable manner, but not in any way that suggests the licensor endorses you or your use.
    ShareAlike — If you remix, transform, or build upon the material, you must distribute your contributions under the same license as the original.
    No additional restrictions — You may not apply legal terms or technological measures that legally restrict others from doing anything the license permits.

See http://creativecommons.org/licenses/by-sa/4.0/
