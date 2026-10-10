# SwingX

Desktop components that pick up where the JDK Swing toolkit stops.

`JXTable` shows and hides columns, packs them to their content, and stripes rows. `JXTreeTable` puts a tree in the hierarchical column. `JXDatePicker` and `JXMonthView` pick dates. `JXTaskPane` groups actions the way a desktop window expects. Painters, highlighters, and autocomplete sit beside those components. The packages are still `org.jdesktop.swingx`, so an application written against SwingLabs SwingX keeps the same imports.

This repository continues that library. The current line is `1.6.6-SNAPSHOT`, the Maven group is `io.github.kamilkrzywanski`, and the code compiles as Java 8. It runs on Java 8 and newer. The license is LGPL-2.1-or-later.

## Add it to a project

Build the snapshot once. JDK 8 or newer and Maven 3 are enough.

```bash
git clone https://github.com/kamilkrzywanski/swingx.git
cd swingx
mvn install
```

```xml
<dependency>
  <groupId>io.github.kamilkrzywanski</groupId>
  <artifactId>swingx-core</artifactId>
  <version>1.6.6-SNAPSHOT</version>
</dependency>
```

`swingx-core` pulls in autocomplete, the action framework, painters, graphics helpers, and look-and-feel support. The library runs on the JDK. An application does not take an extra third-party runtime library with it.

```java
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

JXTable table = new JXTable(model);
table.setColumnControlVisible(true);
table.setHighlighters(HighlighterFactory.createSimpleStriping());

JXDatePicker birthday = new JXDatePicker();
```

## What is in the box

- **Tables and lists.** `JXTable`, `JXTreeTable`, `JXTree`, `JXList`, a column control, highlighters, and sorting.
- **Dates.** `JXDatePicker`, `JXMonthView`.
- **Window chrome.** `JXTaskPane`, `JXTaskPaneContainer`, `JXCollapsiblePane`, `JXTitledPanel`, `JXStatusBar`, `JXHeader`.
- **Input.** Autocomplete on text fields and combo boxes, `JXSearchField`, `JXFindBar`.
- **Feedback.** `JXBusyLabel`, `JXErrorPane`, `JXLoginPane`, `JXTipOfTheDay`, `JXHyperlink`.
- **Painting.** Painters, plus the decorator and highlighter pipeline.

`swingx-beaninfo` holds BeanInfo classes for GUI builders. The demos stay in the source tree and are outside the default reactor.

## Build and test

```bash
mvn test
mvn package
```

The default reactor builds `swingx-common`, `swingx-autocomplete`, `swingx-action`, `swingx-painters`, `swingx-graphics`, `swingx-plaf`, `swingx-core`, `swingx-all`, and `swingx-beaninfo`. Each module jar lands in that module's `target/` directory.

The test suite uses current libraries: JUnit 5.14.4, Hamcrest 3.0, Mockito 4.11.0, and Commons Lang 3.21.0. The Maven plugins are current 3.x releases (compiler 3.16, Surefire 3.6, JAR 3.5, Javadoc 3.12). The exact set is in the root `pom.xml`.

## Issues

Bugs, questions, and ideas go to this repository:

https://github.com/kamilkrzywanski/swingx/issues

## License

LGPL-2.1-or-later. The full text is in [`COPYING`](COPYING). Source files keep their original copyright notices, including those of Sun Microsystems and the other SwingX authors.
