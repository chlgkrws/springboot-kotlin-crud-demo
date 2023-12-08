db.createUser({
    user: 'kot',
    pwd: 'kot123',
    roles: [
        {
            role: 'readWrite',
            db: 'kot-crud'
        }
    ]
});

db = new Mongo().getDB('kot-crud');
db.createCollection('members');
