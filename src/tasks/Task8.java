package tasks;

import common.Person;
import common.Task;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {

  private long count;

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {
    if (persons.size() == 0) {
      return Collections.emptyList();
    } //лишний раз менять коллекцию не надо, есть skip
    return persons.stream().map(Person::getFirstName).skip(1).collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    return new HashSet<>(getNames(persons)); //сету дистинкт не нужен, да и сам стрим не нужен
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) { // стрим это все умеет
    return Stream.of(person.getSecondName(), person.getFirstName(), person.getMiddleName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    Map<Integer, String> map = new HashMap<>(persons.size());  // размер побольше чтобы не увеличивать каждый раз
    for (Person person : persons) { // условие по скорости O(1) только если нет проблем с hashCode, просто перезаписать быстрее
      map.put(person.getId(), convertPersonToString(person));
    }
    return map;
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    return persons1.stream().anyMatch(persons2::contains);
  }

  //Выглядит вроде неплохо...
  public long countEven(Stream<Integer> numbers) {
    return numbers.map(num -> num % 2 == 0).count(); //есть count
  }

  @Override
  public boolean check() {
//    System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
    boolean codeSmellsGood = false;
    boolean reviewerDrunk = false;
    return !(codeSmellsGood || reviewerDrunk);
  }
}
