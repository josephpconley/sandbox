trait UserRepositoryComponent {
  protected val repo: UserRepository

  abstract class UserRepository {
    def find: String
  }

  class DefaultUserRepository extends UserRepository{
    def find = "here is your foo"
  }
}

class UserService {
  this: UserRepositoryComponent =>
  def find: String = repo.find
}

object User extends App {
  val service = new UserService with UserRepositoryComponent {
    val repo = new DefaultUserRepository() //or any other implementation
  }

  println(service.find)
  println(service.repo.find)
}